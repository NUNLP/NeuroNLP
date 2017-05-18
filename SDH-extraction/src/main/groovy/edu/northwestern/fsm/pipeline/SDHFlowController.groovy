package edu.northwestern.fsm.pipeline

import groovy.util.logging.Log4j
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.analysis_engine.TypeOrFeature
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData
import org.apache.uima.cas.Type
import org.apache.uima.fit.component.JCasFlowController_ImplBase
import org.apache.uima.flow.*
import org.apache.uima.jcas.JCas
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.resource.metadata.Capability

@Log4j
class SDHFlowController extends JCasFlowController_ImplBase {

    @Override
    void initialize(FlowControllerContext context) throws ResourceInitializationException {
        super.initialize(context)
        // iterate over available AEs
        Iterator aeIter = getContext().getAnalysisEngineMetaDataMap().entrySet().iterator()
        while (aeIter.hasNext()) {
            Map.Entry entry = (Map.Entry) aeIter.next();
            String aeKey = (String) entry.getKey();
            log.info "Key: ${aeKey}"
        }
    }

    @Override
    Flow computeFlow(JCas aJCas) throws AnalysisEngineProcessException {
        return new SDHFlow()
    }

    class SDHFlow extends JCasFlow_ImplBase {
        private Set mAlreadyCalled = new HashSet()

        @Override
        Step next() throws AnalysisEngineProcessException {
            // Get the CAS that this Flow object is responsible for routing.
            // Each Flow instance is responsible for a single CAS.
            JCas jcas = getJCas()

            // iterate over available AEs
            Iterator aeIter = getContext().getAnalysisEngineMetaDataMap().entrySet().iterator()
            while (aeIter.hasNext()) {
                Map.Entry entry = (Map.Entry) aeIter.next();
                // skip AEs that were already called on this CAS
                String aeKey = (String) entry.getKey();
                if (!mAlreadyCalled.contains(aeKey)) {
                    // check for satisfied input capabilities (i.e. the CAS contains at least one instance
                    // of each required input
                    AnalysisEngineMetaData md = (AnalysisEngineMetaData) entry.getValue()
                    Capability[] caps = md.getCapabilities()
                    boolean satisfied = true;
                    for (int i = 0; i < caps.length; i++) {
                        satisfied = inputsSatisfied(caps[i].getInputs(), jcas)
                        if (satisfied)
                            break
                    }
                    if (satisfied) {
                        mAlreadyCalled.add(aeKey)
                        return new SimpleStep(aeKey)
                    }
                }
            }
            // no appropriate AEs to call - end of flo
            return new FinalStep()
        }

        private boolean inputsSatisfied(TypeOrFeature[] aInputs, JCas jCas) {
            for (int i = 0; i < aInputs.length; i++) {
                TypeOrFeature input = aInputs[i]
                if (input.isType()) {
                    Type type = jCas.getTypeSystem().getType(input.getName())
                    if (type == null)
                        return false
                    Iterator iter = jCas.getIndexRepository().getAllIndexedFS(type)
                    if (!iter.hasNext())
                        return false
                }
            }
            return true
        }
    }
}
