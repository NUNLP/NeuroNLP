import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.SDHSummary

import static edu.northwestern.fsm.domain.SDHConcept.*

SDHSummary summary = jcas.select(type: SDHSummary)[0]
if (summary) {
    summary.thickness = -9

    Collection<SDH> sdhs = jcas.select(type: SDH)
    SDH largestSDH = (sdhs.size() > 0 ? sdhs[0] : null)
    sdhs.each { SDH sdh ->
        if (sdh.thickness != null) {
            if (!largestSDH.thickness) {
                largestSDH = sdh
            }
            String sdhUnit = sdh.thickness.identifier
            Float sdhValue = (sdhUnit == 'MM' ? sdh.thickness.value.toFloat() :
                sdh.thickness.value.toFloat() * 10)
            String largestSDHUnit = largestSDH.thickness.identifier
            Float largestSDHValue = (largestSDHUnit == 'MM' ? largestSDH.thickness.value.toFloat() :
                largestSDH.thickness.value.toFloat() * 10)

            if (sdhValue >= largestSDHValue) {
                largestSDH = sdh
                summary.thickness = sdhValue
            }
        }
    }

    // generate a count
    Set<SDH> uniques = []
    sdhs.each { SDH sdh ->
        if (sdh.side && sdh.thickness) {
            boolean add = true
            uniques.each { SDH unique ->
                if ((sdh.side.identifier == unique.side.identifier) &&
                    (sdh.thickness.identifier == unique.thickness.identifier) &&
                    (sdh.thickness.value == unique.thickness.value)
                ) {
                    add = false
                }
            }
            if (add) { uniques.add(sdh) }
        }
    }
    summary.count = (uniques.size() ?: 1)


    if (largestSDH) {
        // Record side of largest SDH
        if (!largestSDH.side) {
            summary.side = 3
        } else {
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
