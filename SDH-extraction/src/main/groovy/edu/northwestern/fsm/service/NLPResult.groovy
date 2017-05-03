package edu.northwestern.fsm.service

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable


class NLPResults {
    public List<NLPResult> results = []
}

@Immutable
@ToString
class NLPResult {
    public Integer spanStart
    public Integer spanEnd
    public String  spannedText
    public String  mentionClass
    public String  cite
    public String  assertion
}
