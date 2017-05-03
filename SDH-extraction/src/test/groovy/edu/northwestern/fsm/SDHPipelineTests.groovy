package edu.northwestern.fsm

import groovy.util.logging.Log4j
import org.apache.log4j.Level
import org.apache.log4j.PropertyConfigurator
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.jcas.JCas
import org.apache.uima.util.XMLInputSource
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

@Log4j
class PathExtractionPipelineTests {
    static AnalysisEngine engine

    @BeforeClass
    static void configureLogger() {
        def config = new ConfigSlurper().parse(PathExtractionPipelineTests.getResource('/log-config.groovy').text)
        PropertyConfigurator.configure(config.toProperties())
        AggregateBuilder builder = PathExtractionPipelineGenerator.breastPathologyPipeline()
        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/breast/Pipeline.xml'))
        desc.toXML(writer)
        writer.close()
    }

    @Before
    void setup() throws Exception {
        log.setLevel(Level.INFO)
    }

    @BeforeClass static void createPipeline() throws Exception {
        AnalysisEngineDescription desc = UIMAFramework
            .getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(
            PathExtractionPipelineTests.getResource("/descriptors/Pipeline.xml")))
        this.engine = AnalysisEngineFactory.createEngine(desc)
        assert this.engine != null
    }

    @Test void smokeTest() {
        File dir = new File('src/test/resources/data')
        dir.listFiles().each {
            JCas jcas = this.engine.newJCas()
            jcas.setDocumentText(it.text)
            engine.process(jcas)
        }
    }
}
