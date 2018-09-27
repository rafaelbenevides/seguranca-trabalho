import { Moment } from 'moment';

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
}

export class TrainingItem implements ITrainingItem {
    constructor(
        public id?: number,
        public date?: Moment,
        public trainingApplicable?: TrainingApplicable,
        public certificateValidity?: number,
        public hoursOfTraining?: number,
        public trainingId?: number,
        public trainingTypeId?: number
    ) {}
}
