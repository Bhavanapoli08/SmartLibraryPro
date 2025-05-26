import { useState } from 'react';
import { Messages } from './components/Messages';
import { PostNewMessage } from './components/PostNewMessage';

export const MessagesPage = () => {
    const [messagesClick, setMessagesClick] = useState(false);

    return (
        <div className='container'>
            <div className='mt-3 mb-2'>
                <nav>
                    <div className='nav nav-tabs' role='tablist'>
                        <button
                            onClick={() => setMessagesClick(false)}
                            className={`nav-link ${!messagesClick ? 'active' : ''}`}
                            type='button'
                            role='tab'
                        >
                            Submit Question
                        </button>
                        <button
                            onClick={() => setMessagesClick(true)}
                            className={`nav-link ${messagesClick ? 'active' : ''}`}
                            type='button'
                            role='tab'
                        >
                            Q/A Response/Pending
                        </button>
                    </div>
                </nav>
                <div className='tab-content mt-3'>
                    {messagesClick ? (
                        <div className='tab-pane active'>
                            <Messages />
                        </div>
                    ) : (
                        <div className='tab-pane active'>
                            <PostNewMessage />
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};
