package io.directional.wine.region.application.validator

import io.directional.wine.region.domain.Region
import org.springframework.stereotype.Component

@Component
class RegionValidator {
    fun validate(region: Region) {
        check(region.id != region.parent?.id) { "참조 id는 자신의 id가 될 수 없습니다." }
    }
}