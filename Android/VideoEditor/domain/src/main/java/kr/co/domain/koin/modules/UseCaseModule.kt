package kr.co.domain.koin.modules

import kr.co.domain.api.usecase.*
import org.koin.dsl.module

val useCaseModule = module{
    factory { PostLoginUseCase(get()) }

    factory { PostFileUploadUseCase(get())}

    factory { GetFileDownloadUseCase(get()) }

    factory { PostVideoPidNumberAndInfoUseCase(get())}

    factory { PostImagePidNumberAndInfoUseCase(get()) }

    factory { PostImproveVideoPidNumber(get()) }

    factory { PostImageSearchListUseCase(get()) }

    factory { PostVideoSearchListUseCase(get()) }

    factory { PostImproveImagePidNumber(get()) }

}