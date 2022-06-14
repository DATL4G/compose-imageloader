package com.seiko.imageloader.component.fetcher

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.request.Options
import java.io.File
import okio.buffer
import okio.source

internal class FileFetcher(private val data: File) : Fetcher {
    override suspend fun fetch(): FetchResult {
        return FetchSourceResult(
            source = data.inputStream().source().buffer(),
            mimeType = null,
        )
    }

    class Factory : Fetcher.Factory {
        override fun create(data: Any, options: Options, imageLoader: ImageLoader): Fetcher? {
            if (data !is File) return null
            return FileFetcher(data)
        }
    }
}