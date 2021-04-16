import React from "react";
const books =(props)=>{

    return (
        <div className={"container"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
        <table className={"table table-striped table-dark"}>
            <thead>
            <tr>

                <th scope={"col"}>Title</th>
                <th scope={"col"}>Author</th>
                <th scope={"col"}>Genre</th>
                <th scope={"col"}>Availability</th>
            </tr>
            </thead>
            <tbody>
            {props.books.map((term) => {
                return (
                    <tr>
                        <td>{term.name}</td>
                        <td>{term.author.name}</td>
                        <td>{term.category }</td>
                        <td>{term.availableCopies}</td>
                    </tr>
                );
            })}
            </tbody>
        </table>
                </div>
            </div>
        </div>
    );
}
export default books;

