export interface IEmployee {
    id?: number;
    name?: string;
    employeeId?: number;
}

export class Employee implements IEmployee {
    constructor(public id?: number, public name?: string, public employeeId?: number) {}
}
