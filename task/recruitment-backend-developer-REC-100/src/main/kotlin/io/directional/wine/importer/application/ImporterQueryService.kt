package io.directional.wine.importer.application

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.group.GroupBy
import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine._common.utils.QueryDslUtils
import io.directional.wine._common.utils.getSingleResultFrom
import io.directional.wine.importer.application.dto.*
import io.directional.wine.importer.domain.Importer
import io.directional.wine.importer.domain.QImporter
import io.directional.wine.wine.domain.QWine
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ImporterQueryService(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun getById(id: Long): ImporterDetailsResponse {
        val importer = QImporter.importer
        val wine = QWine.wine

        return jpaQueryFactory
            .from(importer)
            .leftJoin(wine).on(importer.id.eq(wine.importer.id))
            .where(importer.id.eq(id))
            .fetchJoin()
            .transform(
                GroupBy.groupBy(importer.id).list(
                    QImporterDetailsResponse(
                        importer,
                        GroupBy.set(QImporterDetailsResponse_WineResponse(wine).skipNulls())
                    )
                )
            )
            .getSingleResultFrom(Importer::class.java)
    }

    fun search(param: ImporterSearchParam, pageable: Pageable): Page<ImporterSummaryResponse> {
        val importer = QImporter.importer

        val searchFilter = searchFilter(param, importer)
        val query = jpaQueryFactory
            .from(importer)
            .where(searchFilter)

        val content = QueryDslUtils.fetchSearchQuery(importer, QImporterSummaryResponse(importer), query, pageable)
        val total = QueryDslUtils.fetchTotalSearchQuery(query, importer)

        return PageImpl(content, pageable, total)
    }

    private fun searchFilter(
        param: ImporterSearchParam,
        importer: QImporter,
    ): BooleanBuilder {
        val builder = BooleanBuilder()

        if (param.name != null) {
            builder.and(importer.name.contains(param.name))
        }

        return builder
    }

}