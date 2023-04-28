import React from "react";
import {Link} from 'react-router-dom';

const book=(props)=>{
    return(
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.author.name} </td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td scope={"col"} className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <Link className={"btn btn-dark ml-2"}
                      onClick={() => props.onMarkAsTaken(props.term.id)}
                      to={`/books`}>
                    MarkAsTaken
                </Link>

            </td>
        </tr>
    )
}

export default book;
