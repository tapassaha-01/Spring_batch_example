export class Employee {
   
    deptCount!:number;
    deptName!:String;
    designation!:String;
    empCount!:number;
    totalSalary!:number;
    constructor(deptcount:number,deptName:String,design:String,empCount:number,totalSalary:number){
        this.deptCount=deptcount
        this.deptName=deptName
        this.designation=design
        this.empCount = empCount
        this.totalSalary = totalSalary
    }
}
