import React, { Component } from "react";
import CityTable from "./Table";
import SignIn from "./Login";
import EditCity from "./EditCity";

class App extends Component {
    render(){
        return(
            <div>
                <h3>Cities List</h3>
                <CityTable/>
            </div>
        )
    }
}

export default App;