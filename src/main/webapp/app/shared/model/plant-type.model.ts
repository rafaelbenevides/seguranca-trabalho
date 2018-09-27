export interface IPlantType {
    id?: number;
    name?: string;
}

export class PlantType implements IPlantType {
    constructor(public id?: number, public name?: string) {}
}
