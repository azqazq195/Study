package io.directional.wine.share.application

import io.directional.wine.share.application.dto.ShareRequest
import io.directional.wine.share.application.mapper.ShareMapper
import io.directional.wine.share.domain.repository.ShareRepository
import io.directional.wine.share.domain.repository.getEntityById
import io.directional.wine.share.exception.NotFoundShareException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ShareService(
    private val shareRepository: ShareRepository,
    private val shareMapper: ShareMapper
) {
    fun create(shareRequest: ShareRequest) {
        val share = shareMapper.dtoToEntity(shareRequest)
        shareRepository.save(share)
    }

    fun update(id: Long, shareRequest: ShareRequest) {
        val share = shareRepository.getEntityById(id)
        val updateValue = shareMapper.dtoToEntity(shareRequest)

        share.update(updateValue)
        shareRepository.save(share)
    }

    fun delete(id: Long) {
        if (!shareRepository.existsById(id)) throw NotFoundShareException()
        shareRepository.deleteById(id)
    }
}