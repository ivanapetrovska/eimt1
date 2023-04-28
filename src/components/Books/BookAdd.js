import React from "react";
import {useNavigate} from "react-router-dom";

const BookAdd=(props)=>{
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category:1,
        author:1,
        availableCopies:0
    })
    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })

    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name= formData.name;
        const  category=formData.category;
        const author=formData.author;
        const availableCopies=formData.availableCopies;
        props.onAddBook(name, category, author, availableCopies);
        history("/books");
    }
    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter book name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                                <option value={term.id}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="firstName">Author First Name</label>
                        <input type="text"
                               className="form-control"
                               id="firstName"
                               name="firstName"
                               required
                               placeholder="Enter author's first name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastName">Author Last Name</label>
                        <input type="text"
                               className="form-control"
                               id="lastName"
                               name="lastName"
                               required
                               placeholder="Enter author's last name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="Available copies"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

    )
}

export default BookAdd;