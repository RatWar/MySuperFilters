package com.besaba.anvarov.mysuperfilters

import android.graphics.Bitmap

interface MySuperFilter {
    fun apply(src: Bitmap): Bitmap?
}