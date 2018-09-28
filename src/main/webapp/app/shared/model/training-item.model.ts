import { Moment } from 'moment';
import { ITrainingType } from 'app/shared/model/training-type.model';

export const enum TrainingApplicable {
    APPLICABLE = 'APPLICABLE',
    NOT_APPLICABLE = 'NOT_APPLICABLE'
}

export interface ITrainingItem {
    id?: number;
    date?: Moment;
    trainingApplicable?: TrainingApplicable;
    certificateValidity?: number;
    hoursOfTraining?: number;
    trainingId?: number;
    trainingTypeId?: number;
    trainingType?: ITrainingType;
}

export class TrainingItem implements ITrainingItem {
    constructor(
        public id?: number,
        public date?: Moment,
        public trainingApplicable?: TrainingApplicable,
        public certificateValidity?: number,
        public hoursOfTraining?: number,
        public trainingId?: number,
        public trainingTypeId?: number,
        public trainingType?: ITrainingType
    ) {}
}
