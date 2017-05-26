package edu.northwestern.fsm

import com.opencsv.CSVReader
import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
import edu.northwestern.fsm.type.SDHSummary
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.jcas.JCas
import org.junit.BeforeClass
import org.junit.Test

class SDHExperiments {
    static class DocumentData {
        public String id
        public Boolean isolatedSDH
        public Integer shift
        public Integer thickness
        public Integer count
        public Integer side
        public Boolean convexity
        public String reportText
    }

    static Collection<DocumentData> trainSet = []
    static Collection<DocumentData> testSet = []

    static Collection<DocumentData> readData(CSVReader reader) {
        Collection<DocumentData> documentData = []
        String [] nextLine
        def headers = reader.readNext()
        while ((nextLine = reader.readNext()) != null) {
            DocumentData data = new DocumentData()
            data.id = nextLine[0]
            data.isolatedSDH = (nextLine[1] == 'true' ? true : false)
            data.shift = (nextLine[2] in ['-9', ''] ? null : nextLine[2].toInteger())
            data.thickness = (nextLine[3] in ['-9', ''] ? null : nextLine[3].toDouble())
            data.count = (nextLine[4] in ['-9', ''] ? null : nextLine[4].toInteger())
            data.side = (nextLine[5] in ['-9', ''] ? null : nextLine[5].toInteger())
            data.convexity = (nextLine[6] == 0 ? false : true)
            data.reportText = nextLine[7]
            documentData << data
        }
        return documentData
    }

    @BeforeClass
    static void setupClass() {
        CSVReader trainSetReader = new CSVReader(new FileReader(
            '/Users/willthompson/Box Sync/SDH NLP Development Project/SDH-training.csv'))
        trainSet = readData(trainSetReader)
        CSVReader testSetReader = new CSVReader(new FileReader(
            '/Users/willthompson/Box Sync/SDH NLP Development Project/SDH-testing.csv'))
        testSet = readData(testSetReader)
    }

    @Test void sdhSideTest() {
        int totalCount = 0
        int numCorrect = 0

        AggregateBuilder builder = SDHExtractionPipelineGenerator.createSDHPipeline()
        AnalysisEngine engine = builder.createAggregate()

        JCas jcas = engine.newJCas()
        trainSet.each { DocumentData dd ->
            jcas.reset()
            jcas.setDocumentText(dd.reportText)
            SDHSummary metaData = new SDHSummary(jcas)
            metaData.documentTitle = dd.id
            metaData.addToIndexes()
            engine.process(jcas)
            metaData = jcas.select(type:SDHSummary)[0]
            totalCount++
            if (metaData.side == dd.side) {
                numCorrect++
            }
            else {
                println "${metaData.documentTitle}: predicted = ${metaData.side}, actual = ${dd.side}"
            }
        }
        println "Accuracy = ${numCorrect/totalCount}"
    }

    @Test void sdhThicknessTest() {
        int totalCount = 0
        int numCorrect = 0

        AggregateBuilder builder = SDHExtractionPipelineGenerator.createSDHPipeline()
        AnalysisEngine engine = builder.createAggregate()

        JCas jcas = engine.newJCas()
        trainSet.each { DocumentData dd ->
            jcas.reset()
            jcas.setDocumentText(dd.reportText)
            SDHSummary metaData = new SDHSummary(jcas)
            metaData.documentTitle = dd.id
            metaData.addToIndexes()
            engine.process(jcas)
            metaData = jcas.select(type:SDHSummary)[0]
            totalCount++
            if (metaData.thickness== dd.thickness) {
                numCorrect++
            }
            else {
                println "${metaData.documentTitle}: predicted = ${metaData.thickness}, actual = ${dd.thickness}"
            }
        }
        println "Accuracy = ${numCorrect/totalCount}"
    }
}
