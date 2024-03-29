package com.besaba.anvarov.mysuperfilters.filters

import android.graphics.Bitmap
import com.besaba.anvarov.mysuperfilters.MySuperFilter

class Blur : MySuperFilter {

    override fun apply(src: Bitmap): Bitmap? {
        val GaussianBlurConfig = arrayOf(
            doubleArrayOf(1.0, 2.0, 1.0),
            doubleArrayOf(2.0, 4.0, 2.0),
            doubleArrayOf(1.0, 2.0, 1.0)
        )
        val convMatrix = ConvolutionMatrix(3)
        convMatrix.applyConfig(GaussianBlurConfig)
        convMatrix.Factor = 16.0
        convMatrix.Offset = 0.0
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix)
    }


}