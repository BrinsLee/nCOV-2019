package com.brins.ncov_2019.model

class KnowledgeResult {


    var result : List<Result>? = null

    class Result {
        var id = 0
        var title = ""
        var imgUrl : String? = null
        var linkUrl = ""
        var description = ""
    }
}