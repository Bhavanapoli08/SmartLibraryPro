import { useState } from 'react';
import MessageModel from '../../../models/MessageModel';

export const PostNewMessage = () => {
    // Temporary placeholders - replace with real auth tomorrow
    const isAuthenticated = true;
    
    const [title, setTitle] = useState('');
    const [question, setQuestion] = useState('');
    const [displayWarning, setDisplayWarning] = useState(false);
    const [displaySuccess, setDisplaySuccess] = useState(false);

    async function submitNewQuestion() {
        const url = `http://localhost:8080/api/messages/secure/add/message`;
        if (isAuthenticated && title !== '' && question !== '') {
            const messageRequestModel: MessageModel = new MessageModel(title, question);
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                    // Add authorization header tomorrow
                },
                body: JSON.stringify(messageRequestModel)
            };

            try {
                const submitNewQuestionResponse = await fetch(url, requestOptions);
                if (!submitNewQuestionResponse.ok) {
                    throw new Error('Something went wrong!');
                }

                setTitle('');
                setQuestion('');
                setDisplayWarning(false);
                setDisplaySuccess(true);
            } catch (error) {
                console.error('Error submitting question:', error);
                setDisplayWarning(true);
            }
        } else {
            setDisplayWarning(true);
            setDisplaySuccess(false);
        }
    }
    
    return (
        <div className='card mt-3'>
            <div className='card-header'>
                Ask question to Luv 2 Read Admin
            </div>
            <div className='card-body'>
                <form method='POST'>
                    {displayWarning && 
                        <div className='alert alert-danger' role='alert'>
                            {title === '' || question === '' 
                                ? 'All fields must be filled out' 
                                : 'Failed to submit question'}
                        </div>
                    }
                    {displaySuccess && 
                        <div className='alert alert-success' role='alert'>
                            Question added successfully
                        </div>
                    }
                    <div className='mb-3'>
                        <label className='form-label'>
                            Title
                        </label>
                        <input 
                            type='text' 
                            className='form-control' 
                            placeholder='Title' 
                            onChange={e => setTitle(e.target.value)} 
                            value={title}
                        />
                    </div>

                    <div className='mb-3'>
                        <label className='form-label'>
                            Question
                        </label>
                        <textarea 
                            className='form-control' 
                            rows={3} 
                            onChange={e => setQuestion(e.target.value)} 
                            value={question}
                        />
                    </div>
                    <div>
                        <button 
                            type='button' 
                            className='btn btn-primary mt-3' 
                            onClick={submitNewQuestion}
                        >
                            Submit Question
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}