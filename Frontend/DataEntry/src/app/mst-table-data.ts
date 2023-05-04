export class MstTableData {

    Depart_list:deptClass[]
    Design_list:designClass[]
    constructor(Depart_list:deptClass[],Design_list:designClass[]){
        this.Depart_list=Depart_list
        this.Design_list = Design_list
    }
}

export class deptClass{
    id!:number
    department!:string
    designation!:any[]
}

export class designClass{
    id!:number
    designName!:string
    dept!:any
}