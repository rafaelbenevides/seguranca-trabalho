export interface ITrainingType {
    id?: number;
    name?: string;
}

export class TrainingType implements ITrainingType {
    constructor(public id?: number, public name?: string) {}
}
