package edu.northwestern.fsm

import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
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
class SDHExtractionPipelineTests {
    static AnalysisEngine engine

    @BeforeClass
    static void configureLogger() {
        def config = new ConfigSlurper().parse(SDHExtractionPipelineTests.getResource('/log-config.groovy').text)
        PropertyConfigurator.configure(config.toProperties())
        AggregateBuilder builder = SDHExtractionPipelineGenerator.sdhPipeline()
        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/SDHPipeline.xml'))
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
            SDHExtractionPipelineTests.getResource("/descriptors/SDHPipeline.xml")))
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
