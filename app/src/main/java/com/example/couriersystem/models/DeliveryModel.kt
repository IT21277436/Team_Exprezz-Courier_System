package com.example.couriersystem.models

data class DeliveryModel (

    var delId: String? = null,
    var rPickup: String? = null,
    var rPkgWeight: String? = null,
    var rCategory: String? = null,
    var rName: String? = null,
    var rAddress: String? = null,
    var rMobile: String? = null,
    var delProgLabel: String? = null,
    var delProgress: Int? = 0

)