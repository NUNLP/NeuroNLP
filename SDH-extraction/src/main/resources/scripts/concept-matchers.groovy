import clinicalnlp.pattern.AnnotationSequencer
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence
import edu.northwestern.fsm.type.Measure
import edu.northwestern.fsm.type.MidlineShift
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.Section
import edu.northwestern.fsm.type.Side

import static clinicalnlp.dsl.DSL.getContains
import static edu.northwestern.fsm.domain.SDHConcept.SEG_FINDINGS
import static edu.northwestern.fsm.domain.SDHConcept.SEG_IMPRESSION
import static edu.northwestern.fsm.logic.Functions.createMentions

//---------------------------------------------------------------------------------------------------------------------
// base pattern matching
//---------------------------------------------------------------------------------------------------------------------

Collection<Section> sections = jcas.select(type:Section, filter:{it.divType in [SEG_FINDINGS, SEG_IMPRESSION]})

createMentions(
    patterns:sdh$patterns,
    jcas:jcas,
    searchSet:sections,
    type:SDH,
    longestMatch:true
)

createMentions(
    patterns:midline$shift$patterns,
    jcas:jcas,
    searchSet:sections,
    type:MidlineShift,
    longestMatch:true
)

createMentions(
    patterns:side$patterns,
    jcas:jcas,
    searchSet:sections,
    type:Side,
    longestMatch:true
)

createMentions(
    patterns:measure$patterns,
    jcas:jcas,
    searchSet:sections,
    type:Measure,
    longestMatch:true
)

jcas.select(type:Measure).each { Measure measure ->
    def m = measure.coveredText =~ /(?i)(.+?)(?:\s*|-)(cm|mm)/
    m.find()
    String num = m.group(1)
    String unit = m.group(2)
    measure.value = num.toFloat()
    measure.identifier = unit.toUpperCase()
}

//---------------------------------------------------------------------------------------------------------------------
// higher order pattern matching
//---------------------------------------------------------------------------------------------------------------------

jcas.select(type:Sentence, filter:(contains(SDH))).each { Sentence sentence ->

    // Side patterns

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


    // SDH thickness patterns

    sequence = new AnnotationSequencer(sentence, [SDH, Side, Measure]).first()
    sdh$size$relation$2.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Measure measure = b.getVariable('measure')[0]
        sdh.thickness = measure
    }
    sdh$size$relation$3.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Measure measure = b.getVariable('measure')[0]
        sdh.thickness = measure
    }
}

jcas.select(type:Sentence, filter:(contains(MidlineShift))).each { Sentence sentence ->
    def sequence = new AnnotationSequencer(sentence, [MidlineShift, Measure]).first()
    midline$shift$size$relation$1.matcher(sequence).each { Binding b ->
        MidlineShift shift = b.getVariable('shift')[0]
        Measure measure = b.getVariable('measure')[0]
        shift.distance = measure
    }
    midline$shift$size$relation$2.matcher(sequence).each { Binding b ->
        MidlineShift shift = b.getVariable('shift')[0]
        Measure measure = b.getVariable('measure')[0]
        shift.distance = measure
    }
}