import React from 'react';
import {Link} from 'react-router-dom';

const bookTerm = (props) => {
    return (
        <tr>
            <td>{term.name}</td>
            <td>{term.author.name}</td>
            <td>{term.category }</td>
            <td>{term.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}

export default bookTerm();