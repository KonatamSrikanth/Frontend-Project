import React, { Component } from 'react';
import EmployeeServices from '../services/EmployeeServices';

class ListEmployeeComponent extends Component {

    constructor(props){
        super (props)
        this.state={
            employees: []
        }

        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee=this.deleteEmployee.bind(this);
    }
    editEmployee( id ){
        this.props.history.push(`/update-employee/${id}`);
    }
    deleteEmployee( id ){
       
        EmployeeServices.deleteEmployee(id).then( res => {
            this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});
        });
    }


    componentDidMount(){
        EmployeeServices.getEmployee().then((res) =>{
 this.setState({employees : res.data});
        }); 
    }
  
    addEmployee(){
        this.props.history.push('/add-employee');
    }
viewEmployee(id){
    this.props.history.push(`/view-employee/${id}`);

}
    render() {
        return (
            <div>
                <h2 class="text-center"> Employees List</h2>
                <div className="button">
                    <button className='btn btn-primary' onClick={this.addEmployee}> Add Employee </button>
                </div>

                < div className ="row">
                    <table className='table table-striped table-bordered'>
        <thead>
        <tr>
            <th> Employee First Name</th>
            <th> Employee Last Name</th>
            <th> Employee Email Id</th>
            <th> Actions</th>
        </tr>
    </thead>

    <tbody>
        {
            this.state.employees.map(
                employee => <tr key ={employee.id}>
                    <td>{employee.firstName}</td>
                    <td>{employee.lastName}</td>
                    <td>{employee.emailId}</td>
                    <td>
                        <button onClick={()=>this.editEmployee(employee.id) }className=" btn btn-info">Update</button>
                        <button style={{marginLeft:"10px"}} onClick={()=>this.deleteEmployee(employee.id) }className=" btn btn-danger">Delete</button>
                        <button style={{marginLeft:"10px"}} onClick={()=>this.viewEmployee(employee.id) }className=" btn btn-info">Employee All Details</button>
                    </td>

                </tr>
            )
        }
    </tbody>
</table>
                </div>
            </div>
        );
    }
}

export default ListEmployeeComponent;