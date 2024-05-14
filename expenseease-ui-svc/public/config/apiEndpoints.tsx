const EXPENSEEASE_IAM_DOMAIN = 'http://localhost:8080';
const EXPENSEEASE_GROUPS_DOMAIN = 'http://localhost:8081';

export const REGISTER_ENDPOINT: string = `${EXPENSEEASE_IAM_DOMAIN}/expenseease-iam-svc/v1/api/register`;
export const LOGIN_ENDPOINT: string = `${EXPENSEEASE_IAM_DOMAIN}/expenseease-iam-svc/v1/api/authenticate`;
export const FETCH_GROUPS_ENDPOINT: string = `${EXPENSEEASE_GROUPS_DOMAIN}/expenseease-group-svc/v1/api/groups`;
export const CREATE_GROUP_ENDPOINT: string = `${EXPENSEEASE_GROUPS_DOMAIN}/expenseease-group-svc/v1/api/groups`;
