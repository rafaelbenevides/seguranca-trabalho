import { ITrainingItem } from 'app/shared/model//training-item.model';

export interface ITraining {
    id?: number;
    items?: ITrainingItem[];
    plantId?: number;
    plantTypeId?: number;
    employeeId?: number;
}

export class Training implements ITraining {
    constructor(
        public id?: number,
        public items?: ITrainingItem[],
        public plantId?: number,
        public plantTypeId?: number,
        public employeeId?: number
    ) {}
}
