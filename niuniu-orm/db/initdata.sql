
delete from lottery_round_item;
delete from lottery_order_detail;
delete from lottery_order;
delete FROM lottery_round;
delete from trade_info;
delete from lottery_order_result;


delete from account_detail where username not in ('system','ymz668','agency1','agency2','agency3','play1','play2','play3','ymz6686','ymz6688');
delete from account_info where username not in ('play1','play2','play3');
delete from offaccount_info where username not in ('system','ymz668','agency1','agency2','agency3','ymz6686','ymz6688');
update account_detail set money=0.0 ;
update account_detail set budget=0.0 ;
update account_detail set money=10000000 where username='ymz668';