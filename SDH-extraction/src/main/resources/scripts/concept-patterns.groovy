import clinicalnlp.pattern.AnnotationPattern
import clinicalnlp.pattern.AnnotationRegex
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token
import edu.northwestern.fsm.type.Measure
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.Side

import static clinicalnlp.pattern.AnnotationPattern.get$A
import static clinicalnlp.pattern.AnnotationPattern.get$N
import static edu.northwestern.fsm.domain.SDHConcept.*

//---------------------------------------------------------------------------------------------------------------------
// base patterns
//---------------------------------------------------------------------------------------------------------------------

def subdural$hemotoma$1 = '''
(convexity|extra-?axial|subdural)(/subarachnoid)?(\\s+hyperdense)?(\\s+tentorial)?
\\s+(hematomas?|hemorrhages?|layerings?|collections?|blood|overlying|lesions?)
'''

def subdural$hemotoma$2 = '''
(subdural)|(the\\s+hematoma)|(this\\s+hematoma)|(hyperdensit(y|ies))|(collections?)
'''

def left$side = '''
(left\\s+frontal\\s+lobe)|
(left\\s+frontotemporoparietal)|
(left)
'''

def right$side = '''
(right\\s+frontal\\s+lobe)|
(right)
'''

def measure = '''
/(?i)\\d+(\\.\\d+)?(cm|mm)/
'''

sdh$patterns = [
    (~"(?ixs)\\b(?:${subdural$hemotoma$1})\\b")   : SUBDURAL_HEMATOMA.map,
    (~"(?ixs)\\b(?:${subdural$hemotoma$2})\\b")   : SUBDURAL_HEMATOMA.map
]

side$patterns = [
    (~"(?ixs)\\b(?:${left$side})\\b")           : LEFT.map,
    (~"(?ixs)\\b(?:${right$side})\\b")          : RIGHT.map
]

measure$patterns = [
    (~"(?ixs)\\b(?:${left$side})\\b")           : LEFT.map,
    (~"(?ixs)\\b(?:${right$side})\\b")          : RIGHT.map
]

//---------------------------------------------------------------------------------------------------------------------
// higher order patterns
//---------------------------------------------------------------------------------------------------------------------

AnnotationRegex sdh$side$relation$1 = new AnnotationRegex((AnnotationPattern)
    $N('sdh', $A(SDH)) & $N('side', $A(Side))
)

AnnotationRegex sdh$side$relation$2 = new AnnotationRegex((AnnotationPattern)
    $N('side', $A(Side)) & $N('sdh', $A(SDH))
)

AnnotationRegex sdh$size$relation$0 = new AnnotationRegex((AnnotationPattern)
    $N('numunit', $A(Token, [text:/(?i)\d+(\.\d+)?(cm|mm)/]))
)

AnnotationRegex sdh$size$relation$1 = new AnnotationRegex((AnnotationPattern)
    $N('num', $A(Token, [text:/\d+(\.\d+)?/])) & $N('unit', $A(Token, [text:/(?i)cm|mm/]))
)

AnnotationRegex sdh$size$relation$2 = new AnnotationRegex((AnnotationPattern)
    $N('sdh', $A(SDH)) & $A(Side)(0,2) & $N('measure', $A(Measure))
)

AnnotationRegex sdh$size$relation$3 = new AnnotationRegex((AnnotationPattern)
    $N('measure', $A(Measure)) & $A(Side)(0,2) & $N('sdh', $A(SDH))
)

// ---------------------------------------------------------------------------------------------------------------------
// binding variable map
// ---------------------------------------------------------------------------------------------------------------------
[
    sdh$patterns:sdh$patterns,
    side$patterns:side$patterns,
    sdh$side$relation$1:sdh$side$relation$1,
    sdh$side$relation$2:sdh$side$relation$2,
    sdh$size$relation$0:sdh$size$relation$0,
    sdh$size$relation$1:sdh$size$relation$1,
    sdh$size$relation$2:sdh$size$relation$2,
    sdh$size$relation$3:sdh$size$relation$3
]