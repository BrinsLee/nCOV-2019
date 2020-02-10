package com.brins.ncov_2019.model

class ProvincesResult {

    var provinceName = ""
    var provinceShortName = ""
    var confirmedCount = 0
    var suspectedCount = 0
    var curedCount = 0
    var deadCount = 0
    var locationId = 0
    var cities : List<city>? = null
    var isExpend = false

    class city {
        var cityName = ""
        var confirmedCount = 0
        var suspectedCount = 0
        var curedCount = 1046
        var deadCount = 0
        var locationId = 0

    }
}