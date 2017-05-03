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
    
    static final String CODING_SCHEME_SNOMED = 'SNOMED'
    static final String CODING_SCHEME_UCUM = 'UCUM'
    
    static final String SEG_FINAL_DIAGNOSIS = 'FINAL_DIAGNOSIS'
    static final String SEG_CLINICAL_HISTORY = 'CLINICAL_HISTORY'

    static final String TUI_NEOPLASTIC_PROCESS = 'T191'
    static final String TUI_DISEASE_OR_SYNDROME = 'T047'
    static final String TUI_BODY_PART = 'T023'
    static final String TUI_CLASSIFICAION = 'T185'
    static final String TUI_QUALITATIVE_CONCEPT = 'T080'
    static final String TUI_QUANTITATIVE_CONCEPT = 'T081'
    static final String TUI_SPATIAL_CONCEPT = 'T082'
    static final String TUI_DIAGNOSTIC_PROCEDURE = 'T060'
    static final String TUI_PREVENTIVE_PROCEDURE = 'T061'
    static final String TUI_LAB_PROCEDURE = 'T059'

    // ----------------------------------------------------------------------------------------------------------------
    // GENERAL ATTRIBUTES
    // ----------------------------------------------------------------------------------------------------------------
    
    static final SDHConcept POSITIVE_VALUE = new SDHConcept(cite:'Positive', codeSystem:CODING_SCHEME_SNOMED, code:'10828004', cui:'C1446409', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept NEGATIVE_VALUE = new SDHConcept(cite:'Negative', codeSystem:CODING_SCHEME_SNOMED, code:'260385009', cui:'C0205160', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept POSSIBLE_VALUE = new SDHConcept(cite:'Possible', codeSystem:CODING_SCHEME_SNOMED, code:'371930009', cui:'C0332149', tui:TUI_QUALITATIVE_CONCEPT)
    static final SDHConcept GRADE = new SDHConcept(cite:'Grade', codeSystem:CODING_SCHEME_SNOMED, code:'103421006', cui:'C0441800', tui:TUI_CLASSIFICAION)
    static final SDHConcept PERCENTAGE = new SDHConcept(cite:'Percentage unit', codeSystem:CODING_SCHEME_SNOMED, code:'415067009', cui:'C1532336', tui:TUI_QUANTITATIVE_CONCEPT)

}