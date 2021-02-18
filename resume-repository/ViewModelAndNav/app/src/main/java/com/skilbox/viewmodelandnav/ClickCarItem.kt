package com.skilbox.viewmodelandnav

class ClickCarItem(
    _itLongClick: Boolean,
    _id: Long,
    _carImage: String,
    _carBrend: String,
    _carName: String,
    _carEngine_capacity: String
) {

    var itLongClick: Boolean
    var id: Long
    var carImage: String
    var carBrend: String
    var carName: String
    var carEngine_capacity: String

    init {
        itLongClick = _itLongClick
        id = _id
        carImage = _carImage
        carBrend = _carBrend
        carName = _carName
        carEngine_capacity = _carEngine_capacity
    }
}
