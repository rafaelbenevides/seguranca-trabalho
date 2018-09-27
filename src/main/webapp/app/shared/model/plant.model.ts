export interface IPlant {
    id?: number;
    name?: string;
}

export class Plant implements IPlant {
    constructor(public id?: number, public name?: string) {}
}
