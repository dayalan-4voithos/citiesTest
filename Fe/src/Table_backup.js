import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import {getCitiesWithPagination,getCitiesByName} from "./service/CityService";
const columns = [
    { id: 'id', label: 'ID', minWidth: 170 },
    {
        id: 'cityName',
        label: 'City Name',
        minWidth: 170,
        align: 'right',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'photo',
        label: 'Photo',
        minWidth: 170,
        align: 'right',
        format: (value) => value.toLocaleString('en-US'),
    },
];

function createData(id, cityName, photo) {
    return {id, cityName, photo};
}

const rows = [{"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},
    {"id":1,"cityName":"Tokyo","photo":"url"}, {"id": 1, "cityName": "Tokyo", "photo": "url"},];

export default function ColumnGroupingTable() {
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
        console.log("Page Change",newPage)
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
        console.log("test")
    };

    return (
        <Paper sx={{ width: '100%' }}>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{  minWidth: column.minWidth }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.format && typeof value === 'number'
                                                        ? column.format(value) : value}
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
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </Paper>
    );
}
