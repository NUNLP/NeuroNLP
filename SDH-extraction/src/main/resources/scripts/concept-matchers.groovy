import clinicalnlp.dsl.DSL
import clinicalnlp.pattern.AnnotationSequencer
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.Section
import edu.northwestern.fsm.type.Side

import static edu.northwestern.fsm.domain.SDHConcept.LEFT
import static edu.northwestern.fsm.domain.SDHConcept.SEG_IMPRESSION
import static edu.northwestern.fsm.domain.SDHConcept.getSUBDURAL_HEMATOMA
import static edu.northwestern.fsm.logic.Functions.createConceptMentions
import static clinicalnlp.dsl.DSL.*

//---------------------------------------------------------------------------------------------------------------------
// base pattern matching
//---------------------------------------------------------------------------------------------------------------------

createConceptMentions(
    patterns:sdh$patterns,
    jcas:jcas,
    searchSet:jcas.select(type:Section, filter:{it.divType == SEG_IMPRESSION}),
    type:SDH,
    longestMatch:true
)

createConceptMentions(
    patterns:side$patterns,
    jcas:jcas,
    searchSet:jcas.select(type:Section, filter:{it.divType == SEG_IMPRESSION}),
    type:Side,
    longestMatch:true
)

//---------------------------------------------------------------------------------------------------------------------
// second order pattern matching
//---------------------------------------------------------------------------------------------------------------------

jcas.select(type:Sentence, filter:and(contains(SDH), contains(Side))).each { Sentence sentence ->
    def sequence = new AnnotationSequencer(sentence, [SDH, Side]).first()
    sdh$side$relation$1.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Side side = b.getVariable('side')[0]
        sdh.side = side
    }
    sdh$side$relation$2.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Side side = b.getVariable('side')[0]
        sdh.side = side
    }
}
