import { useEffect, useState } from 'react';
import AdminMessageRequest from '../../../models/AdminMessageRequest';
import MessageModel from '../../../models/MessageModel';
import { Pagination } from '../../Utils/Pagination';
import { SpinnerLoading } from '../../Utils/SpinnerLoading';
import { AdminMessage } from './AdminMessage';

export const AdminMessages = () => {
    // Temporary placeholders - replace with real auth tomorrow
    const isAdmin = true;
    
    // Normal Loading Pieces
    const [isLoadingMessages, setIsLoadingMessages] = useState(true);
    const [httpError, setHttpError] = useState(null);
    
    // Messages endpoint State
    const [messages, setMessages] = useState<MessageModel[]>([]);
    const [messagesPerPage] = useState(5);

    // Pagination
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    // Recall useEffect
    const [btnSubmit, setBtnSubmit] = useState(false);

    useEffect(() => {
        const fetchUserMessages = async () => {
            if (isAdmin) {
                const url = `http://localhost:8080/api/messages/search/findByClosed/?closed=false&page=${currentPage - 1}&size=${messagesPerPage}`;
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                        // Add authorization header tomorrow
                    }
                };
                try {
                    const messagesResponse = await fetch(url, requestOptions);
                    if (!messagesResponse.ok) {
                        throw new Error('Something went wrong!');
                    }
                    const messagesResponseJson = await messagesResponse.json();
                    setMessages(messagesResponseJson._embedded.messages);
                    setTotalPages(messagesResponseJson.page.totalPages);
                } catch (error: any) {
                    setHttpError(error.message);
                }
            }
            setIsLoadingMessages(false);
        }
        fetchUserMessages();
        window.scrollTo(0, 0);
    }, [currentPage, btnSubmit]);

    if (isLoadingMessages) {
        return <SpinnerLoading/>;
    }

    if (httpError) {
        return (
            <div className='container m-5'>
                <p>{httpError}</p>
            </div>
        );
    }

    async function submitResponseToQuestion(id: number, response: string) {
        const url = `http://localhost:8080/api/messages/secure/admin/message`;
        if (isAdmin && id !== null && response !== '') {
            try {
                const messageAdminRequestModel: AdminMessageRequest = new AdminMessageRequest(id, response);
                const requestOptions = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                        // Add authorization header tomorrow
                    },
                    body: JSON.stringify(messageAdminRequestModel)
                };

                const messageAdminRequestModelResponse = await fetch(url, requestOptions);
                if (!messageAdminRequestModelResponse.ok) {
                    throw new Error('Something went wrong!');
                }
                setBtnSubmit(!btnSubmit);
            } catch (error) {
                console.error('Error submitting response:', error);
            }
        }
    }

    const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

    return (
        <div className='mt-3'>
            {messages.length > 0 ? 
                <>
                    <h5>Pending Q/A: </h5>
                    {messages.map(message => (
                        <AdminMessage 
                            message={message} 
                            key={message.id} 
                            submitResponseToQuestion={submitResponseToQuestion}
                        />
                    ))}
                </>
                :
                <h5>No pending Q/A</h5>
            }
            {totalPages > 1 && <Pagination currentPage={currentPage} totalPages={totalPages} paginate={paginate}/>}
        </div>
    );
}