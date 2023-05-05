export class MstTableData {

    Depart_list:string[]
    Design_list:string[]
    constructor(Depart_list:string[],Design_list:string[]){
        this.Depart_list=Depart_list
        this.Design_list = Design_list
    }
}

export class deptClass{
    
    department!:string
    
}

export class designClass{
    
    designName!:string
   
}