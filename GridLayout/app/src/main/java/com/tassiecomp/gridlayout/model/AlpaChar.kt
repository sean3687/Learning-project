package com.tassiecomp.gridlayout.model

class AlphaChar {
    // fist value will be iconchar and second value will be alphachar

    // this code is when iconchaar or alpha char is null value.
    var iconsChar:Int? = 0
    var alphaChar:String? = null

    constructor(iconChar: Int?, alphaChar:String?){
        this.iconsChar = iconChar // define it is int
        this.alphaChar = alphaChar //define it is string
    }
}