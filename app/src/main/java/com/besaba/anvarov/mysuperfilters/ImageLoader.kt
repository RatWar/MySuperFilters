package com.besaba.anvarov.mysuperfilters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

object ImageLoader {

    // Модификатор suspend – помечает функцию как приостанавливаемую, которую можно использовать в корутинах.
    // Обычно, те методы, которые будут выполнять какую-то асинхронную работу помечают модификатором suspend.
    // Функция withContext() – применяется для смены контекста для запуска переданного в функцию блока кода.
    // Контекстом называется набор параметров с которыми запускается корутина, в данном случае,
    // с помощью функции withContext(Dispatchers.IO) мы меняем поток выполнения на Dispatchers.IO
    // ( поток ввода-вывода)  так как делать запросы в сеть нужно именно из него
    // Dispatchers.IO – диспетчер потоков для управления корутинами.
    // Для каждой корутины не создаются новые потоки, наоборот, диспетчеры помогают переиспользовать
    // уже созданные потоки для выполнения корутин. Существует 4 типа диспетчеров.

    // Виды диспетчеров:
    // Default: используется, если не был указан явно ни один из диспетчеров.
    //   Используется для вычислений на CPU (например для обработки картинок), для вычисления,
    //   выполнения различных алгоритмов. Использует примерно столько же потоков,
    //   сколько ядер процессора на устройстве
    // IO: используется для сетевых запросов, запросов в базу данных, чтения файлов.
    //   Пул потоков ограничен 64 потоками. Как андроид – разработчики чаще всего будете использовать
    //   именно этот диспетчер.
    // Unconfined: если вы указали такой тип диспетчера, то какой поток будет использоваться
    //   будет выбрано рандомно, так что используйте его только если это вам действительно нужно.
    // Main: этот тип диспетчера используется для обновления пользовательского интерфейса
    //   в главном потоке. В Android-приложении корутны, запущенные с данным диспетчером
    //   будут выполнены в UI-потоке.
    suspend fun getOriginalBitmapAsync(url: String): Bitmap =
        withContext(Dispatchers.IO) {
            URL(url).openStream().use {
                return@withContext BitmapFactory.decodeStream(it)
            }
        }
}