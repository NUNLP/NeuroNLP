package edu.northwestern.fsm.pipeline

import clinicalnlp.dsl.ScriptAnnotator
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory
import org.apache.uima.pear.util.UIMAUtil
import org.apache.uima.resource.metadata.TypeSystemDescription

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
            add(createEngineDescription(OpenNlpPosTagger,
                OpenNlpPosTagger.PARAM_MODEL_LOCATION,
                "classpath:/models/mayo-pos.zip")
            )
            add(
                createEngineDescription(StanfordParser,
                StanfordParser.PARAM_MODEL_LOCATION,
                'classpath:/edu/stanford/nlp/models/lexparser/englishRNN.ser.gz')
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
        AnalysisEngineDescription engineDescription = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/SDHPipeline.xml'))
        engineDescription.toXML(writer)
        writer.close()

        TypeSystemDescription typeSystemDescription = TypeSystemDescriptionFactory.createTypeSystemDescription()
        writer = new PrintWriter(new File('src/main/resources/descriptors/SDHTypeSystem.xml'))
        typeSystemDescription.toXML(writer)
        writer.close()
    }
}
