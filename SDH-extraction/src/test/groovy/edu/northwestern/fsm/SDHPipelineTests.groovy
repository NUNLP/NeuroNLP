package edu.northwestern.fsm

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token
import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
import edu.northwestern.fsm.type.Section
import groovy.util.logging.Log4j
import org.apache.log4j.Level
import org.apache.log4j.PropertyConfigurator
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.cas.TypeSystem
import org.apache.uima.cas.impl.TypeSystemImpl
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.tcas.Annotation_Type
import org.apache.uima.json.JsonCasSerializer
import org.apache.uima.resource.metadata.TypeSystemDescription
import org.apache.uima.util.XMLInputSource
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

@Log4j
class SDHExtractionPipelineTests {
    static AnalysisEngine engine

    @BeforeClass
    static void setupClass() {
        def config = new ConfigSlurper().parse(SDHExtractionPipelineTests.getResource('/log-config.groovy').text)
        PropertyConfigurator.configure(config.toProperties())
        AggregateBuilder builder = SDHExtractionPipelineGenerator.createSDHPipeline()
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
        AnalysisEngineDescription desc = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(
            SDHExtractionPipelineTests.getResource("/descriptors/SDHPipeline.xml")))
        this.engine = AnalysisEngineFactory.createEngine(desc)
        assert this.engine != null
    }

    @Test void smokeTest() {
        TypeSystemDescription typeSystemDescription = TypeSystemDescriptionFactory.createTypeSystemDescription()
        File dir = new File('src/test/resources/data/input')
        OutputStream outputStream = new FileOutputStream(new File("src/test/resources/data/output/test.json"))
        dir.listFiles().each {
            JCas jcas = this.engine.newJCas()
            jcas.setDocumentText(it.text)
            jcas.setDocumentLanguage('en')
            engine.process(jcas)
            TypeSystem filterTypeSystem = new TypeSystemImpl()
            filterTypeSystem.addType(Section.canonicalName, Annotation_Type.typeIndexID)
            JsonCasSerializer.jsonSerialize(jcas.cas, filterTypeSystem, outputStream)
        }
    }
}
