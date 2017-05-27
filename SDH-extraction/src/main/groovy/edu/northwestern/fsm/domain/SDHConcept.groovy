package edu.northwestern.fsm.domain

import groovy.transform.Immutable
import groovy.transform.ToString

@ToString
@Immutable
class SDHConcept {

    String cite
    String cui
    String tui
    String codeSystem
    String code
    
    Map getMap() {
        return [cite:cite, cui:cui, tui:tui, codeSystem:codeSystem, code:code, tui:tui]
    }

    // ----------------------------------------------------------------------------------------------------------------
    // Values
    // ----------------------------------------------------------------------------------------------------------------
    static final String CODING_SCHEME_SNOMED = 'SNOMED'
    static final String CODING_SCHEME_LOINC = 'LOINC'
    static final String CODING_SCHEME_UCUM = 'UCUM'

    static final String SEG_FINDINGS = 'FINDINGS'
    static final String SEG_IMPRESSION = 'IMPRESSION'
    static final String SEG_PROVIDERS = 'PROVIDERS'
    static final String SEG_CLINICAL_HISTORY = 'CLINICAL_HISTORY'

    static final String TUI_DISEASE_OR_SYNDROME = 'T047'
    static final String TUI_BODY_PART = 'T023'
    static final String TUI_CLASSIFICAION = 'T185'
    static final String TUI_QUALITATIVE_CONCEPT = 'T080'
    static final String TUI_QUANTITATIVE_CONCEPT = 'T081'
    static final String TUI_SPATIAL_CONCEPT = 'T082'
    static final String TUI_DIAGNOSTIC_PROCEDURE = 'T060'
    static final String TUI_PREVENTIVE_PROCEDURE = 'T061'
    static final String TUI_LAB_PROCEDURE = 'T059'
    static final String TUI_FINDING = 'T033'
    static final String TUI_PATHOLOGIC_FUNCTION = 'T046'

    // ----------------------------------------------------------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------------------------------------------------------
    static final SDHConcept POSITIVE_VALUE = new SDHConcept(cite:'Positive', codeSystem:CODING_SCHEME_SNOMED, code:'10828004', cui:'C1446409', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept NEGATIVE_VALUE = new SDHConcept(cite:'Negative', codeSystem:CODING_SCHEME_SNOMED, code:'260385009', cui:'C0205160', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept POSSIBLE_VALUE = new SDHConcept(cite:'Possible', codeSystem:CODING_SCHEME_SNOMED, code:'371930009', cui:'C0332149', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept LEFT = new SDHConcept(cite:'Left', codeSystem:CODING_SCHEME_SNOMED, code:'7771000', cui:'C0450414', tui:TUI_SPATIAL_CONCEPT)
    static final SDHConcept RIGHT = new SDHConcept(cite:'Right', codeSystem:CODING_SCHEME_SNOMED, code:'24028007', cui:'C0450415', tui:TUI_SPATIAL_CONCEPT)
    static final SDHConcept SMALL = new SDHConcept(cite:'Small', codeSystem:CODING_SCHEME_SNOMED, code:'255507004', cui:'C0700321', tui:TUI_QUANTITATIVE_CONCEPT)
    static final SDHConcept LARGE = new SDHConcept(cite:'Large', codeSystem:CODING_SCHEME_SNOMED, code:'255509001', cui:'C0549177', tui:TUI_QUANTITATIVE_CONCEPT)
    static final SDHConcept MEASURE = new SDHConcept(cite:'Measure', codeSystem:CODING_SCHEME_UCUM, code:'', cui:'', tui:TUI_QUANTITATIVE_CONCEPT)

    // ----------------------------------------------------------------------------------------------------------------
    // Findings
    // ----------------------------------------------------------------------------------------------------------------
    static final SDHConcept SUBDURAL_HEMATOMA = new SDHConcept(cite:'Subdural_hematoma', codeSystem:CODING_SCHEME_SNOMED, code:'95453001', cui:'C0018946', tui:TUI_PATHOLOGIC_FUNCTION)
    static final SDHConcept MIDLINE_SHIFT = new SDHConcept(cite:'Midline_shift', codeSystem:CODING_SCHEME_SNOMED, code:'299728009', cui:'C0576481', tui:TUI_FINDING)
}