import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.SDHSummary

import static edu.northwestern.fsm.domain.SDHConcept.*

SDHSummary summary = jcas.select(type:SDHSummary)[0]
if (summary) {
    SDH largestSDH = null
    Collection<SDH> sdhs = jcas.select(type:SDH)
    sdhs.each { SDH sdh ->
        if (!largestSDH) { largestSDH = sdh }
        else if (sdh.thickness > largestSDH.thickness) {
            largestSDH = sdh
        }
    }

    if (largestSDH) {
        // Record thickness of largest SDH
        if (largestSDH.thickness == 0) {
            summary.thickness = -9
        }
        else {
            summary.thickness = largestSDH.thickness
        }

        // Record size of largest SDH
        if (!largestSDH.side) { summary.side = 3 }
        else {
            switch (largestSDH.side.identifier) {
                case LEFT.code:
                    summary.side = 1
                    break
                case RIGHT.code:
                    summary.side = 2
                    break
            }
        }
    }
}
