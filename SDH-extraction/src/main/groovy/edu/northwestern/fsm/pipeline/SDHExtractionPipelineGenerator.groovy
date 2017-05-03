package edu.northwestern.fsm.pipeline

import clinicalnlp.dsl.ae.LocalDSLAnnotator
import clinicalnlp.token.ae.LocalTokenAnnotator
import clinicalnlp.types.Segment
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.fit.factory.ExternalResourceFactory
import org.apache.uima.resource.ExternalResourceDescription

/**
 * Static methods for generating pathology concept extraction NLP pipeline
 */
class SDHExtractionPipelineGenerator {
    private SDHExtractionPipelineGenerator() {}

    /**
     * Breast pathology concept extraction pipeline
     * @return
     */
    static AggregateBuilder breastPathologyPipeline() {
        ExternalResourceDescription tokenResDesc = ExternalResourceFactory.createExternalResourceDescription(
            opennlp.uima.tokenize.TokenizerModelResourceImpl.class, "file:clinicalnlp/models/en-token.bin")
        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            // ---------------------------------------------------------------------------------------------------------
            // document segmenter
            // ---------------------------------------------------------------------------------------------------------
            add(AnalysisEngineFactory.createEngineDescription(LocalDSLAnnotator,
                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'scripts/segmenter.groovy'))
            // ---------------------------------------------------------------------------------------------------------
            // tokenizer
            // ---------------------------------------------------------------------------------------------------------
            add(AnalysisEngineFactory.createEngineDescription(
                LocalTokenAnnotator,
                LocalTokenAnnotator.PARAM_CONTAINER_TYPE,
                Segment.canonicalName,
                LocalTokenAnnotator.PARAM_SPLIT_PATTERN, /[,\-\/\\:\.\(\)\+]/,
                LocalTokenAnnotator.TOKEN_MODEL_KEY, tokenResDesc))
            // ---------------------------------------------------------------------------------------------------------
            // basic concept recognizer
            // ---------------------------------------------------------------------------------------------------------
//            add(AnalysisEngineFactory.createEngineDescription(LocalDSLAnnotator,
//                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'breast/base-concepts.groovy'))
//            // ---------------------------------------------------------------------------------------------------------
//            // complex concept recognizer
//            // ---------------------------------------------------------------------------------------------------------
//            add(AnalysisEngineFactory.createEngineDescription(LocalDSLAnnotator,
//                LocalDSLAnnotator.PARAM_BINDING_SCRIPT_FILE, 'breast/concept-patterns.groovy',
//                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'breast/concept-matchers.groovy'))
//            // ---------------------------------------------------------------------------------------------------------
//            // assertion status recognizer
//            // ---------------------------------------------------------------------------------------------------------
//            add(AnalysisEngineFactory.createEngineDescription(LocalDSLAnnotator,
//                LocalDSLAnnotator.PARAM_BINDING_SCRIPT_FILE, 'breast/assertion-patterns.groovy',
//                LocalDSLAnnotator.PARAM_SCRIPT_FILE, 'breast/assertion-matchers.groovy'))
        }
        return builder
    }

    /**
     * Generate NLP pipeline descriptors
     * @param args
     */
    static void main(args) {
        AggregateBuilder builder = breastPathologyPipeline()
        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/Pipeline.xml'))
        desc.toXML(writer)
        writer.close()
    }
}
