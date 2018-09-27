export interface IJobRole {
    id?: number;
    name?: string;
}

export class JobRole implements IJobRole {
    constructor(public id?: number, public name?: string) {}
}
