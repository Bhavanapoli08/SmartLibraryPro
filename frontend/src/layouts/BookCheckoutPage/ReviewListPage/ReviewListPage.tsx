import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'; // ✅ Proper routing param
import ReviewModel from '../../../models/ReviewModel';
import { Pagination } from '../../Utils/Pagination';
import { Review } from '../../Utils/Review';
import { SpinnerLoading } from '../../Utils/SpinnerLoading';

export const ReviewListPage = () => {
    const { bookId } = useParams(); // ✅ Get bookId from route

    const [reviews, setReviews] = useState<ReviewModel[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState<string | null>(null);

    // Pagination
    const [currentPage, setCurrentPage] = useState(1);
    const [reviewsPerPage] = useState(5);
    const [totalAmountOfReviews, setTotalAmountOfReviews] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        const fetchBookReviewsData = async () => {
            if (!bookId) return;

            try {
                const reviewUrl = `http://localhost:8080/api/reviews/search/findByBookId?bookId=${bookId}&page=${currentPage - 1}&size=${reviewsPerPage}`;
                const responseReviews = await fetch(reviewUrl);

                if (!responseReviews.ok) {
                    throw new Error('Something went wrong while fetching reviews!');
                }

                const responseJsonReviews = await responseReviews.json();
                const responseData = responseJsonReviews._embedded.reviews;

                setTotalAmountOfReviews(responseJsonReviews.page.totalElements);
                setTotalPages(responseJsonReviews.page.totalPages);

                const loadedReviews: ReviewModel[] = responseData.map((review: any) => ({
                    id: review.id,
                    userEmail: review.userEmail,
                    date: review.date,
                    rating: review.rating,
                    book_id: review.book_id,
                    reviewDescription: review.reviewDescription,
                }));

                setReviews(loadedReviews);
                setIsLoading(false);
            } catch (error: any) {
                setIsLoading(false);
                setHttpError(error.message);
            }
        };

        fetchBookReviewsData();
    }, [currentPage, bookId]);

    if (isLoading) {
        return <SpinnerLoading />;
    }

    if (httpError) {
        return (
            <div className='container m-5'>
                <p>{httpError}</p>
            </div>
        );
    }

    const indexOfLastReview: number = currentPage * reviewsPerPage;
    const indexOfFirstReview: number = indexOfLastReview - reviewsPerPage;
    const lastItem = Math.min(indexOfLastReview, totalAmountOfReviews);

    const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

    return (
        <div className="container mt-5">
            <div>
                <h3>Comments: ({totalAmountOfReviews})</h3>
            </div>
            <p>
                {indexOfFirstReview + 1} to {lastItem} of {totalAmountOfReviews} items:
            </p>
            <div className="row">
                {reviews.map(review => (
                    <Review review={review} key={review.id} />
                ))}
            </div>
            {totalPages > 1 && (
                <Pagination
                    currentPage={currentPage}
                    totalPages={totalPages}
                    paginate={paginate}
                />
            )}
        </div>
    );
};
