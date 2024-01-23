package io.directional.wine.region.application

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine._common.utils.QueryDslUtils
import io.directional.wine._common.utils.getSingleResultFrom
import io.directional.wine.grape.domain.QGrape
import io.directional.wine.region.application.dto.*
import io.directional.wine.region.domain.QRegion
import io.directional.wine.region.domain.Region
import io.directional.wine.share.domain.QShare
import io.directional.wine.wine.domain.QWine
import io.directional.wine.winery.domain.QWinery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RegionQueryService(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getById(id: Long): RegionDetailsResponse {
        val region = QRegion.region
        val grape = QGrape.grape
        val share = QShare.share1
        val wine = QWine.wine
        val winery = QWinery.winery
        val parentRegion = QRegion("parentRegion")

        return jpaQueryFactory
            .from(region)
            .leftJoin(parentRegion).on(region.id.eq(parentRegion.id))
            .leftJoin(share).on(region.id.eq(share.region.id))
            .leftJoin(grape).on(share.grape.id.eq(grape.id))
            .leftJoin(wine).on(region.id.eq(wine.region.id))
            .leftJoin(winery).on(region.id.eq(winery.region.id))
            .where(region.id.eq(id))
            .fetchJoin()
            .transform(
                GroupBy.groupBy(region.id).list(
                    QRegionDetailsResponse(
                        region,
                        GroupBy.set(QRegionDetailsResponse_GrapeResponse(grape).skipNulls()),
                        GroupBy.set(QRegionDetailsResponse_WineryResponse(winery).skipNulls()),
                        GroupBy.set(QRegionDetailsResponse_WineResponse(wine).skipNulls()),
                    )
                )
            )
            .getSingleResultFrom(Region::class.java)
    }

    fun search(param: RegionSearchParam, pageable: Pageable): Page<RegionSummaryResponse> {
        val region = QRegion.region
        val parentRegion = QRegion("parentRegion")

        val searchFilter = searchFilter(param, region, parentRegion)
        val query = jpaQueryFactory
            .from(region)
            .leftJoin(region.parent, parentRegion)
            .where(searchFilter)

        val content = QueryDslUtils.fetchJoinSearchQuery(region, QRegionSummaryResponse(region), query, pageable)
        val total = QueryDslUtils.fetchTotalSearchQuery(query, region)

        return PageImpl(content, pageable, total)
    }

    private fun searchFilter(
        param: RegionSearchParam,
        region: QRegion,
        parentRegion: QRegion,
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        if (param.nameKorean != null) {
            builder.and(region.nameKorean.contains(param.nameKorean))
        }
        if (param.nameEnglish != null) {
            builder.and(region.nameEnglish.contains(param.nameEnglish))
        }
        if (param.parentNameKorean != null) {
            builder.and(parentRegion.nameKorean.contains(param.parentNameKorean))
        }
        if (param.parentNameEnglish != null) {
            builder.and(parentRegion.nameEnglish.contains(param.parentNameEnglish))
        }

        return builder
    }
}

