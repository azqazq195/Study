package io.directional.wine.grape.application

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine._common.utils.QueryDslUtils
import io.directional.wine._common.utils.getSingleResultFrom
import io.directional.wine.grape.application.dto.*
import io.directional.wine.grape.domain.Grape
import io.directional.wine.grape.domain.QGrape
import io.directional.wine.region.domain.QRegion
import io.directional.wine.share.domain.QShare
import io.directional.wine.wine.domain.QWine
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GrapeQueryService(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getById(id: Long): GrapeDetailsResponse {
        val grape = QGrape.grape
        val wine = QWine.wine
        val share = QShare.share1
        val region = QRegion.region

        return jpaQueryFactory
            .from(grape)
            .leftJoin(grape.wines, wine)
            .leftJoin(share).on(grape.id.eq(share.grape.id))
            .leftJoin(region).on(share.region.id.eq(region.id))
            .where(grape.id.eq(id))
            .fetchJoin()
            .transform(
                GroupBy.groupBy(grape.id).list(
                    QGrapeDetailsResponse(
                        grape,
                        GroupBy.set(QGrapeDetailsResponse_RegionResponse(region).skipNulls()),
                        GroupBy.set(QGrapeDetailsResponse_WineResponse(wine).skipNulls())
                    )
                )
            )
            .getSingleResultFrom(Grape::class.java)
    }

    fun search(param: GrapeSearchParam, pageable: Pageable): Page<GrapeSummaryResponse> {
        val grape = QGrape.grape
        val share = QShare.share1
        val region = QRegion.region

        val searchFilter = searchFilter(param, grape, region)
        val query = jpaQueryFactory
            .from(grape)
            .leftJoin(share).on(grape.id.eq(share.grape.id))
            .leftJoin(region).on(share.region.id.eq(region.id))
            .where(searchFilter)

        val content = QueryDslUtils.transformSearchQuery(
            grape,
            GroupBy.groupBy(grape.id).list(
                QGrapeSummaryResponse(
                    grape,
                    GroupBy.set(QGrapeSummaryResponse_RegionResponse(region).skipNulls()),
                )
            ),
            query,
            pageable
        )
        val total = QueryDslUtils.fetchTotalSearchQuery(query, grape)

        return PageImpl(content, pageable, total)
    }

    private fun searchFilter(
        param: GrapeSearchParam,
        grape: QGrape,
        region: QRegion,
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        if (param.grapeNameKorean != null) {
            builder.and(grape.nameKorean.contains(param.grapeNameKorean))
        }

        if (param.grapeNameEnglish != null) {
            builder.and(grape.nameEnglish.contains(param.grapeNameEnglish))
        }

        if (param.regionNameKorean != null) {
            builder.and(region.nameKorean.contains(param.regionNameKorean))
        }

        if (param.regionNameEnglish != null) {
            builder.and(region.nameEnglish.contains(param.regionNameEnglish))
        }

        return builder
    }
}