package edu.northwestern.fsm.pipeline

import clinicalnlp.dsl.ScriptAnnotator
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription

/**
 * Static methods for generating pathology concept extraction NLP pipeline
 */
class SDHExtractionPipelineGenerator {
    private SDHExtractionPipelineGenerator() {}

    /**
     * Breast pathology concept extraction pipeline
     * @return
     */
    static AggregateBuilder createSDHPipeline() {
        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            add(createEngineDescription(ScriptAnnotator,
                ScriptAnnotator.PARAM_SCRIPT_FILE, 'scripts/segmenter.groovy')
            )
            add(createEngineDescription(OpenNlpSegmenter,
                OpenNlpSegmenter.PARAM_SEGMENTATION_MODEL_LOCATION,
                "classpath:/models/sd-med-model.zip",
                OpenNlpSegmenter.PARAM_TOKENIZATION_MODEL_LOCATION,
                "classpath:/models/en-token.bin")
            )
        }
        return builder
    }

    /**
     * Generate NLP pipeline descriptors
     * @param args
     */
    static void main(args) {
        AggregateBuilder builder = createSDHPipeline()
        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/SDHPipeline.xml'))
        desc.toXML(writer)
        writer.close()
    }
}
