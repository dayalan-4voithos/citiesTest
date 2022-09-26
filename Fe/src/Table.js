import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import axios from "axios";
import {Component} from "react";
import TextField from "@mui/material/TextField";

const CITY_API = 'http://localhost:8080/api/city/';
const CSRF_API = 'http://localhost:8080/api/security/csrf';
const columns = [
    {id: 'id', label: 'ID', minWidth: 170},
    {
        id: 'name',
        label: 'City Name',
        minWidth: 170,
        align: 'left',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'photo',
        label: 'Photo',
        minWidth: 170,
        align: 'center',
        format: (value) => value.toLocaleString('en-US'),
    },
];

function createData(id, cityName, photo) {
    return {id, cityName, photo};
}


class CityTable extends Component {
    constructor(props) {
        super(props);
        //this.state = {"data": [{"id": "ind", "cityName": "sds", "photo": "dasds"}], "page": 0, "rowsPerPage": 10}
        this.state = {"data": [], "page": 0, "rowsPerPage": 10, "totalNumOfPages": 10}
        this.getCitiesWithPagination(0, 10);
        console.log(this.state)
    }

    handleChangePage = (event, newPage) => {
        console.log("Page Changed to", newPage)
        this.setState({"page": newPage});
        if (newPage > this.state.page) {
            this.getCitiesWithPagination(newPage, this.state.rowsPerPage);
        }

    }
    handleChangeRowsPerPage = (event) => {
        this.setState({"rowsPerPage": +event.target.value, "page": 0});
        console.log("test")
        this.getCitiesWithPagination(this.state.page, +event.target.value);
    };

    handleKeyUp = (event) => {
        let value = event.target.value;
        if (value != "") {
            this.getCitiesByName(value)
        } else {
            this.getCitiesWithPagination(0, 10);
        }
        console.log(this.state)
    };

    getCitiesWithPagination(pageNumber, pageSize) {
        var self = this;
        var axios = require('axios');
        axios.get(CSRF_API)
            .then(tokenResp => {
                var data = JSON.stringify({
                    "pageNumber": pageNumber,
                    "pageSize": pageSize
                });
                let postConfig = {
                    headers: {
                        'X-CSRF-TOKEN': tokenResp.data.token,
                        'Content-Type': 'application/json'
                    }
                }
                return axios.post(CITY_API + 'getCitiesPaginated', data, postConfig);
            })
            .then(response => {

                const result = this.state.data.concat(response.data.results);
                const totalNumOfPages = response.data.pagination.totalPages;
                self.setState({"data": result, "totalNumOfPages": totalNumOfPages});
            })
    }

    getCitiesByName(name) {
        var self = this;
        var axios = require('axios');
        var config = {
            method: 'get',
            url: CITY_API + 'getByName?name=' + name,
            headers: {
                'Authorization': 'Basic dXNlcjpwYXNzd29yZA==',

            }
        };
        axios(config)
            .then(function (response) {
                const result = response.data;
                self.setState({"data": result, "totalNumOfPages": result.length});
            })
            .catch(function (error) {
                console.log(error);
            });

    }

    render() {
        return (
            <Paper sx={{width: '100%'}}>
                <TableContainer sx={{maxHeight: 440}}>
                    <Table stickyHeader aria-label="sticky table">
                        <TableHead>
                            <TableRow>
                                <TextField id="outlined-basic" label="Search" variant="outlined" size="small"
                                           onChange={this.handleKeyUp}/>
                            </TableRow>
                            <TableRow key={"r1"}>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{minWidth: column.minWidth}}

                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.data
                                .slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage)
                                .map((row) => {
                                    return (
                                        <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                            {columns.map((column) => {
                                                const value = row[column.id];
                                                return (

                                                    <TableCell key={column.id} align={column.align}>
                                                        {column.id != 'photo' ? column.format && typeof value === 'number' ? column.format(value) : value :
                                                            <img style={{width: "50px", height: "50px"}} src={value}/>}
                                                    </TableCell>
                                                );
                                            })}
                                        </TableRow>
                                    );
                                })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={this.state.totalNumOfPages}
                    rowsPerPage={this.state.rowsPerPage}
                    page={this.state.page}
                    onPageChange={this.handleChangePage}
                    onRowsPerPageChange={this.handleChangeRowsPerPage}
                />
            </Paper>
        );
    }
}

export default CityTable;