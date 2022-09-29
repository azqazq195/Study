import { Controller } from '@nestjs/common';
import { Crud, CrudController } from '@nestjsx/crud';

import { BoardEntity } from './board.entity';
import { BoardService } from './board.service';

// @Crud({
//     model: {
//         type: BoardEntity,
//     },
// })
@Controller('boards')
export class BoardController {
    constructor(private readonly service: BoardService) {
        this.service.greeting(1);
    }
}
